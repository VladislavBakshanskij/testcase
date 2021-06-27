package com.example.journalconsumer.listener;

import com.example.journalconsumer.app.SearchInfo;
import com.example.journalconsumer.configuration.AmqpConfiguration;
import com.example.journalconsumer.service.audit.AuditService;
import com.example.journalconsumer.service.journal.JournalCreateDto;
import com.example.journalconsumer.service.journal.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JournalQueueListener {
    private final JournalService journalService;
    private final AuditService auditService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "journal_input")
    public void listen(final SearchInfo searchInfo) {
        execute(() -> rabbitTemplate.convertAndSend(AmqpConfiguration.JOURNAL_OUTPUT.getExchangeName(),
                AmqpConfiguration.JOURNAL_OUTPUT.getQueueName(),
                journalService.all(searchInfo.getLimit(), searchInfo.getOffset())));
    }

    @RabbitListener(queues = "journal_create")
    public void listenCreate(final JournalCreateDto dto) {
        execute(() -> auditService.save(dto));
    }

    private void execute(Executor e) {
        try {
            e.execute();
        } catch (Exception exception) {
            rabbitTemplate.convertAndSend(AmqpConfiguration.ERROR.getExchangeName(),
                    AmqpConfiguration.ERROR.getQueueName(), e);
        }
    }

    interface Executor {
        void execute() throws Exception;
    }
}
