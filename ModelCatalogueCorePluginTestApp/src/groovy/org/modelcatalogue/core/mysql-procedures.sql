-- DELIMITER $$

DROP FUNCTION IF EXISTS GetAllDestinations $$

CREATE FUNCTION GetAllDestinations (InitialQueue TEXT, DescendType LONG, LeafType LONG) RETURNS TEXT
DETERMINISTIC
  BEGIN

    DECLARE rv,q,queue,queue_children,leaves TEXT;
    DECLARE queue_length,front_id,pos LONG;

    SET rv = '';
    SET queue = InitialQueue;
    SET queue_length = LENGTH(InitialQueue) - LENGTH(REPLACE(InitialQueue, ',', '')) + 1;

    WHILE queue_length > 0 DO
      SET front_id = ROUND(queue,0);
      IF queue_length = 1 THEN
        SET queue = '';
      ELSE
        SET pos = LOCATE(',',queue) + 1;
        SET q = SUBSTR(queue,pos);
        SET queue = q;
      END IF;

      SET queue_length = queue_length - 1;

      SELECT IFNULL(qc,'') INTO queue_children
      FROM (SELECT GROUP_CONCAT(destination_id) qc
            FROM relationship WHERE source_id = front_id AND relationship_type_id = DescendType) A;

      SELECT IFNULL(l,'') INTO leaves
      FROM (SELECT GROUP_CONCAT(destination_id) l
            FROM relationship WHERE source_id = front_id AND relationship_type_id = LeafType) A;

      IF LENGTH(queue_children) = 0 THEN
        IF LENGTH(queue) = 0 THEN
          SET queue_length = 0;
        END IF;
      ELSE
        IF LENGTH(rv) = 0 THEN
          SET rv = leaves;
        ELSEIF LENGTH(leaves) > 0 THEN
          SET rv = CONCAT(rv,',',leaves);
        END IF;
        IF LENGTH(queue) = 0 THEN
          SET queue = queue_children;
        ELSE
          SET queue = CONCAT(queue,',',queue_children);
        END IF;
        SET queue_length = LENGTH(queue) - LENGTH(REPLACE(queue,',','')) + 1;
      END IF;
    END WHILE;

    RETURN rv;

  END $$

DROP FUNCTION IF EXISTS GetTopLevelDataClasses $$
CREATE FUNCTION GetTopLevelDataClasses (DataModelId LONG, DescendType LONG) RETURNS TEXT
DETERMINISTIC
  BEGIN
    RETURN (
      select group_concat(DISTINCT ce.id) from catalogue_element ce join data_class dc on ce.id = dc.id
      where ce.data_model_id = DataModelId
      and ce.id not in (
        select r.destination_id from relationship r join catalogue_element src on r.source_id = src.id
        where src.data_model_id = DataModelId AND r.relationship_type_id = DescendType
      )
  );
  END $$


-- DELIMITER ;

