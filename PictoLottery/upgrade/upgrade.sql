--奖项与优惠产品关联
CREATE TABLE `test`.`coupon_type_discount_rel` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `COUPON_TYPE_ID` INT NOT NULL,
  `DISCOUNT_PRODUCT_ID` INT NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `coupon_type_discount_rel` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,0,5),(6,4,6);

--删掉优惠产品的奖项id字段
ALTER TABLE `test`.`discount_product` DROP COLUMN `coupon_type_id`;

