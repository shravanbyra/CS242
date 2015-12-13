<?php
include "server.php";

$user = 'abcd';
$password = 'shravanb';
$steamid = '1890';

if($_SERVER["REQUEST_METHOD"] == "POST") {
		$user = $_POST['username'];
		$password = $_POST['password'];
		$steamid = $_POST['steam-id'];
	}

$userQuery = 'INSERT INTO users (username ,reputation ,steam_id ,password)
VALUES ("' . $user .'",  0,  "' . $steamid . '",  "' . $password .'")';

if ($queryResults = $db->query($userQuery )) {
	echo 'Account Successfully created, Welcome ' . $user . '';
} else {
	echo "Registration failed, please try again";
}

$path = ' > register';
?>

<!DOCTYPE html>
<html>
<head>
	<?php
	include "head.php";
	include "header.php";
	include "registerForm.php";
	echo $head;
	?>
</head>
<body>
	<div id="container">
		<?php
		echo $header;
		
		echo $registerForm;		
		
		$db->close();
		?>
	</div>
</body>
</html>