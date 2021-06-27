delete from keyauto.auth_user;
select setval('keyauto.auth_user_id_seq', 1);
delete from keyauto.journal;
select setval('keyauto.journal_id_seq', 1);
