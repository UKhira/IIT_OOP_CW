public class User {
    protected String userId;
    protected String password;
    protected boolean vendorFlag, customerFlag;

    public User(){}

    public User(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

     protected void setFlags(){
         Vendor vendor = new Vendor(userId,password);
         vendorFlag = vendor.checkUser();
         System.out.println(vendorFlag);
     }
}
