package com.learn.chapter.eight.strange;

public class Validator {

    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s) {
        return validationStrategy.execute(s);
    }

    public static void main(String[] args) {

        /**
         * 在没有类继承下的写法
         */
        Validator v = new Validator(new ValidationStrategy() {
            @Override
            public boolean execute(String s) {
                return s.matches("[a-z]+");
            }
        });
        //lambda写法
        Validator v1 = new Validator((String s) -> s.matches("[a-z]+]"));
        boolean flag1 = v1.validate("aaaaa");
        Validator v2 = new Validator((String s) -> s.matches("^[1-9]\\d*$"));
        boolean flag2 = v2.validate("5");
        System.out.println(flag1+"...."+flag2);

        /**
         * 非lamdba
         */
        /*Validator validator = new Validator(new IsAllLowerCase());
        boolean flag = validator.validate("sss");


        Validator v = new Validator(new IsNumeric());
        boolean flag2 = v.validate("5");
        System.out.println(flag+"...."+flag2);*/

    }
}
