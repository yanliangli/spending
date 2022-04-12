export class GlobalConstants {

    private static hostname: string = "http://localhost:2241";
      

    public static spendingUrl: string = this.hostname + "/api/app/spending/v1/spending/";

    public static transactionsUrl: string = this.hostname + "/api/app/spending/v1/transaction/";

}