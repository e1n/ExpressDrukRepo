alter table E1N.product_parameter_value add constraint product_parameter_fk foreign key (product_parameter_id) references E1N.product_parameter (id); 