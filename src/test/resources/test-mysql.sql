BEGIN;
TRUNCATE countries;

INSERT INTO `countries` (`id`, `create_by`, `created_date`, `last_modified_by`, `last_modified_date`, `name`, `population`) VALUES
    (1, 'test', '2018-11-17 18:50:06', 'test', '2018-11-17 18:50:10', 'Mexico', 130497248),
    (2, 'test', '2018-11-17 18:50:06', 'test', '2018-11-17 18:50:10', 'Spain', 49067981),
    (3, 'test', '2018-11-17 18:50:06', 'test', '2018-11-17 18:50:10', 'Colombia', 46070146);

COMMIT;