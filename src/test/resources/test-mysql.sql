BEGIN;
TRUNCATE countries;

INSERT INTO `countries` (`id`, `name`, `population`) VALUES
    (1, 'Mexico', 130497248),
    (2, 'Spain', 49067981),
    (3, 'Colombia', 46070146);

COMMIT;