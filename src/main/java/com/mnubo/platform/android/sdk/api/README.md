# The API

The `MnuboAPI` offers multiple operations interface that you can use to query the mnubo REST API.
Here is a list of all these operation's functions as well as a description of their usage and
behavior.

For extensive documentation about parameters, return types, and possible exception, please refer to
the javadoc.

------------------------------------------------------------------------------

## UserOperations
User operations are mostly going to reach the `/users` endpoint on the mnubo API.

### finUserObjects
This function is used to retrieve the list of the user's `SmartObjects`. There are multiple
signatures allowing you to customize the request to your needs.

Called url = GET : /users/{username}/objects

### getUser
This function is used to fetch the user, if found, matching the given username.

Called url = GET : /users/{username}

### update
This function is used to update the user's information, if found, that matches the given username.

Called url = PUT : /users/{username}

### updatePassword
This function is used to update the user's password, if found, that matches the given
username.

Called url = PUT : /users/{username}/password


------------------------------------------------------------------------------

## SmartObjectOperations
SmartObjectOperations are used to work with the objects you track in your mnubo API. Those objects
are very generic. A SmartObject matches an object model that you have previously defined. We
recommend using the `SmartObject` in a decorator pattern (see the `Phone.java` model in the
demo provided with this SDK).

Note that the SdkId required is an utility class to ensure the query is properly built. The
`SmartObjectOperations` use either the `object_id` or the `device_id` `IdType` to perform queries.

The `device_id` is a natural id like `my_object` you give to your object. The `object_id` is an
unique `UUID` generated by the mnubo API.

### findObject
This function allows you to retrieve an object.

Called url = GET : /objects/{id}

### update
This function allows you to udpate an existing objects.

Called url = PUT : /objects/{id}

### searchSamples
This functions allows you retrieve the samples previously recorded for an object's sensor.

Called url = GET : /objects/{id}/sensors/{sensorname}/samples

### addSamples
This function allows you to add data to an object's sensor. The samples list can be as big as you
want.

Called url = POST : /objects/{id}/sensors/{sensorname}/samples

### addSampleOnPublicSensor
This functions allows you to add a single sample per request to an object's sensor that is
publicly available.

Called url = POST : /objects/{objectId}/sensors/{sensorName}/sample
### createObject
This functions allows you to add an object on the mnubo API.

Called url = POST : /objects


------------------------------------------------------------------------------

## ClientOperations
These operations allows you only four functions. These functions perform queries against the mnubo
API using a client token. This client token is generated with the use of the consumer id and
consumer secret provided in the initialization of the SDK.

This client token has very limited access to the mnubo API.

### createUser
This function allows you to register an user to the mnubo API.

Called url = POST : /users/{username}
### confirmUserCreation
This function confirm user registration. If two step registration is enabled, the registered user
will receive an email containing a token. This token must be used to confirm the user registration.

Called url = POST : /users/{username}/confirmation
### resetPassword
This function is used to initiate the password reset for a give user. This user should receive an
email containing a token.

Called url = DELETE : /users/{username}/password
### confirmPasswordReset
This function is used to complete the password reset. With the token, a new password and its
confirmation, you can change the user password.

Called url = POST : /users/{username}/password


------------------------------------------------------------------------------

## AuthenticationOperations
### logIn
This function will log in on the behalf of the user. This process uses the consumer key and consumer
secret to authenticate the application during the login process.

Called url = POST : /oauth/token
### logOut
This function will terminate the current user connection.

### isUserConnected
This functions tells you whether or not the user is currently connected.

### getUsername
This functions gives you the username currently logged in.