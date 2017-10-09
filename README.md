# Velotrade-sdk

## Recent Update
 As of this current code is beta. Please take care yourself :)

# Installing

You can complie it yourself, here's how:

    $ git clone https://github.com/VeloxManagementLimited/api-sdk-java.git
    $ cd api-sdk-java
    $ ./gradlew fatJar
    
You can see the jar file at folder

    build/libs/
    
# Quickstart

```java
String baseUrl = "https://devapi.velotrade.com";
String username = "your username";
String password = "your md5 password";
VelotradePublicAPI api = new VelotradePublicAPIImpl(baseUrl, username, password);    
    
```

### getDebtorContact

```java
DebtorContact result = api.getDebtorContact(id);
```

### Create auction
```java

String issueDate = "2017-09-28T16:00:00.000Z";
String dueDate = "2017-11-27T16:00:00.000Z";
String expectedPaymentDate = "2017-11-27T16:00:00.000Z";
DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();

Date today = new Date();
String id = "oa1a6a170-d3d4-428a-835f-35ab021d410c";
Invoice invoice = new Invoice();
invoice.setNumber("TEST");
invoice.setIssueDate(parser.parseDateTime(issueDate).toDate());
invoice.setCurrency("USD");
invoice.setAmount(10000);
invoice.setExpectedAmount(10000);
invoice.setPaymentTerms(6000);
invoice.setDueDate(parser.parseDateTime(dueDate).toDate());
invoice.setExpectedPaymentDate(parser.parseDateTime(expectedPaymentDate).toDate());
invoice.setDescription("TEST");


String result = null;
try {
    Attachment attachment = api.uploadAttachment("<file path>");
    DebtorContact debtorContact = api.getDebtorContact(id);
    Auction auction = new Auction(debtorContact, invoice, attachment, attachment, attachment);

    result = api.createAuction(auction);

} catch (Exception e) {
    e.printStackTrace();
}
```

Datetime: http://ronnieroller.com/java/date-times#time-conversions_jdk-7-jodatime
