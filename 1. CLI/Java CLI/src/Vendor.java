public class Vendor extends User{

    protected String userIdq = "V1212";
    protected String passWordq = "PW1234";

    protected Vendor(String userId, String passWord){
        this.userIdq = userId;
        this.passWordq = passWord;
    }

    public boolean checkUser() {
//        if (super.userId.equals(this.userId) && super.password.equals(this.password)) {
//            return true;
//        } else {
//            return false;
//        }
        System.out.println(userId);
        System.out.println(userIdq);
        return false;
    }
}
