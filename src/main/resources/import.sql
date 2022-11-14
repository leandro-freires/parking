INSERT INTO public.ticket (id, payed, payment_time, ticket_number) VALUES (nextval('ticket_seq'), true, '2022-11-01 11:15:18', '301362');
INSERT INTO public.parking (id, entry_time, exit_time, exited, license_number, ticket_id) VALUES (nextval('parking_seq'), '2022-11-01 08:11:43', '2022-11-01 11:21:03', true, 'ABC1D23', 1);

INSERT INTO public.ticket (id, payed, payment_time, ticket_number) VALUES (nextval('ticket_seq'), true, '2022-11-01 09:56:11', '458192');
INSERT INTO public.parking (id, entry_time, exit_time, exited, license_number, ticket_id) VALUES (nextval('parking_seq'), '2022-11-01 08:00:11', '2022-11-01 10:01:57', true, 'KEO4436', 2);

INSERT INTO public.ticket (id, payed, payment_time, ticket_number) VALUES (nextval('ticket_seq'), true, '2022-11-04 13:32:38', '609942');
INSERT INTO public.parking (id, entry_time, exit_time, exited, license_number, ticket_id) VALUES (nextval('parking_seq'), '2022-11-04 12:16:31', '2022-11-04 13:36:01', true, 'KEO4436', 3);

INSERT INTO public.ticket (id, payed, payment_time, ticket_number) VALUES (nextval('ticket_seq'), true, '2022-11-05 17:55:12', '706730');
INSERT INTO public.parking (id, entry_time, exit_time, exited, license_number, ticket_id) VALUES (nextval('parking_seq'), '2022-11-05 14:03:01', '2022-11-05 18:00:59', true, 'ABC1D23', 4);

INSERT INTO public.ticket (id, payed, payment_time, ticket_number) VALUES (nextval('ticket_seq'), true, '2022-11-06 10:11:41', '869044');
INSERT INTO public.parking (id, entry_time, exit_time, exited, license_number, ticket_id) VALUES (nextval('parking_seq'), '2022-11-06 09:15:33', '2022-11-06 10:18:05', true, 'ABC1D23', 5);

INSERT INTO public.ticket (id, payed, payment_time, ticket_number) VALUES (nextval('ticket_seq'), true, '2022-11-06 21:56:06', '858205');
INSERT INTO public.parking (id, entry_time, exit_time, exited, license_number, ticket_id) VALUES (nextval('parking_seq'), '2022-11-06 16:44:28', '2022-11-06 22:02:32', true, 'ABC1D23', 6);
