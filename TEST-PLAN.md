# Users-Transactions API sandbox Test Plan


## Endpoints:


### Users


<table>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/users
   </td>
   <td>POST
   </td>
   <td>Creates a single user
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Create user, provide all need values (name + balance)
   </td>
   <td>User should be created with balance that was provided
   </td>
   <td><p style="text-align: right">
201</p>

   </td>
  </tr>
  <tr>
   <td>Create user, provide only name
   </td>
   <td>User creation should fail, balance value is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Create user, provide only balance
   </td>
   <td>User creation should fail, name value is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Create user, provide name and negative value for balance
   </td>
   <td>User creation should fail, balance cannot be a negative number when creating a user
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Create user, provide name and value with decimal for balance
   </td>
   <td>User creation should fail, balance must be a natural number
   </td>
   <td><p style="text-align: right">
500</p>

   </td>
  </tr>
  <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/users
   </td>
   <td>GET
   </td>
   <td>Retrieve multiple users
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Retrieve all users
   </td>
   <td>A list of existing users and their details should be returned
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Retrieve all users, use query string parameter - existing user/s
   </td>
   <td>A list of the relevant user/s and their details should be returned
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Retrieve all users, use query string parameter - non-exisent user/s
   </td>
   <td>An empty list should be returned
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/users/{id}
   </td>
   <td>GET
   </td>
   <td>Retrieve a single user
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Retrieve user - existing id
   </td>
   <td>User details should be returned
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Retrieve user - non-exisent id
   </td>
   <td>User retrieval should fail
   </td>
   <td><p style="text-align: right">
404</p>

   </td>
  </tr>
  <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/users/{id}
   </td>
   <td>PATCH
   </td>
   <td>Update a single user
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Update existing user name
   </td>
   <td>User name should be updated
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Update existing user balance - valid balance
   </td>
   <td>User balance should be updated
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Update existing user, provide only name
   </td>
   <td>Update user should fail, balance value is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Update existing user, provide only balance
   </td>
   <td>Update user should fail, name value is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Update existing user, provide name and negative value for balance
   </td>
   <td>Update user should fail, balance cannot be a negative number
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Update existing user, provide name and value with decimal for balance
   </td>
   <td>Update user should fail, balance must be a natural number
   </td>
   <td><p style="text-align: right">
500</p>

   </td>
  </tr>
  <tr>
   <td>Update user - provide non-exisent user id
   </td>
   <td>Update user should fail
   </td>
   <td><p style="text-align: right">
404</p>

   </td>
  </tr>
  <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/users/{id}
   </td>
   <td>DELETE
   </td>
   <td>Delete a single user
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Delete user - existing id
   </td>
   <td>User should be deleted
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Delete user - non-exisent id
   </td>
   <td>User deletion should fail
   </td>
   <td><p style="text-align: right">
404</p>

   </td>
  </tr>
</table>



---


### Transactions


<table>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/transactions/create
   </td>
   <td>POST
   </td>
   <td>Create a transaction
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Create transaction - provide valid values (source, destination, amount)
   </td>
   <td>Transaction id should be returned, amount provided should be deducted from source and added to destination
   </td>
   <td><p style="text-align: right">
201</p>

   </td>
  </tr>
  <tr>
   <td>Create transaction - provide amount higher than the source's balance
   </td>
   <td>Transaction should fail
   </td>
   <td><p style="text-align: right">
500</p>

   </td>
  </tr>
  <tr>
   <td>Create transaction - provide negative amount higher than the destination's balance
   </td>
   <td>Transaction should fail
   </td>
   <td><p style="text-align: right">
500</p>

   </td>
  </tr>
  <tr>
   <td>Create transaction - provide value with deicmal for amount
   </td>
   <td>Transaction should fail, amount must be a natural number
   </td>
   <td><p style="text-align: right">
500</p>

   </td>
  </tr>
  <tr>
   <td>Create transaction - provide only destination and amount
   </td>
   <td>Transaction should fail, source is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Create transaction - provide only source and amount
   </td>
   <td>Transaction should fail, destination is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>Create transaction - provide only source and destination
   </td>
   <td>Transaction should fail, amount is mandatory
   </td>
   <td><p style="text-align: right">
400</p>

   </td>
  </tr>
  <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/transactions/{txId}
   </td>
   <td>GET
   </td>
   <td>Retrieve transaction details
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Retrieve transaction details - provide existing txId
   </td>
   <td>Transaction details should be returned
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Retrieve transaction details - provide non-existent txId
   </td>
   <td>Transaction retrieval should fail
   </td>
   <td><p style="text-align: right">
404</p>

   </td>
  </tr>
  <tr>
   <td>
   </td>
   <td>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><strong>url</strong>
   </td>
   <td><strong>method</strong>
   </td>
   <td><strong>action</strong>
   </td>
  </tr>
  <tr>
   <td>/transactions/status/{txId}
   </td>
   <td>GET
   </td>
   <td>Retrieve transaction status
   </td>
  </tr>
  <tr>
   <td><strong>Test description</strong>
   </td>
   <td><strong>Expected result</strong>
   </td>
   <td><strong>Status code</strong>
   </td>
  </tr>
  <tr>
   <td>Retrieve transaction status - provide existing txId
   </td>
   <td>Transaction status should be returned
   </td>
   <td><p style="text-align: right">
200</p>

   </td>
  </tr>
  <tr>
   <td>Retrieve transaction status - provide non-existent txId
   </td>
   <td>Transaction status retrieval should fail
   </td>
   <td><p style="text-align: right">
404</p>

   </td>
  </tr>
</table>