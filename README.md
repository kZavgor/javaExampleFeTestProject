### This is a simple example of a selenium based java testing framework

Here few test cases automated as an example of tests for Google Gmail

#####Check if attachment which exceed 25Mb cannot be send

1. Log into your gmail account.
2. Click compose new email.
3. Specify 'To' field, enter subject and body.
4. Click the 'Attached Files' icon and upload file which is more then 25Mb size:

######*Expected Results:* check if the popup message 'Include file which you try to send exceed the required size (25)'

#####Inlcuding link into email and use it
1. Log into your gmail account.
2. Click compose new email.
3. Specify 'To' field with your other email account, enter subject and body.
4. Put mose cursor into the Body field and click the 'Include link' icon.
5. Specify the 'Text to display' as 'GOOGLE' that will be use as link label .
6. Check the 'Web Address' radio button and enter 'www.google.com' and click OK.
7. Send email and then open sent email.
8. Click the GOOGLe link in the email body.

######*Expected Results:* check if google page opens in the browser window.

To run the tests do a `mvn test`

Tests execution results generates in html format and store in a `/build/reports/chrome/html/index.html`



