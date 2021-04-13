package constants;

public class Constant {
    public static class TimeoutVariables{
        public  static final int IMPLICIT_WAIT = 5;
        public  static final int EXPLICIT_WAIT = 10;
    }

    public static class Urls {
//        public static final String LOGIN_PAGE_URL= "https://www.ocado.com/webshop/startWebshop.do?clkInTab=ocado";
        public static final String LOGIN_PAGE_URL=
        "http://k8s-kubesyst-albingre-20a87abfe5-2022848426.us-east-1.elb.amazonaws.com/login";
    }

    public class TestDataUserRoles {
        public static final String BUYER = "Buyer";
        public static final String CATEGORY_ASSISTANT = "Category Assistant";
    }
}
