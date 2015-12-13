<?php

$registerForm = 
	'<div id="register-form">
		<h3>New User Registration</h3>
		<form name="register-form" class="user-form" action="" method="post">
			<input type="text" name="username" placeholder="username"/><br/>
			<input type="text" name="password" placeholder="password"/><br/>
			<input type="text" name="steam-id" placeholder="steamId"/><br/>
			
			<input type="submit" name="submit" value="Register"/>
			
			<br/>
			<br/>
			<span id="required-fields-notice">* indicates a required field</span>
		</form>
	</div>';

?>