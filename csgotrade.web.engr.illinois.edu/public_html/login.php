<?php
include "server.php";
$path = ' > login';

$user = '';
	$pw = '';
	if($_SERVER["REQUEST_METHOD"] == "POST") {
		$user= $_POST['username'];
		$pw= $_POST['password'];
	}
		
	
	$query = 'SELECT * from users WHERE username = "'. $user .'" AND password = "' . $pw . '"';
	
	if ($queryResults = $db->query($query)) {
		$currentUser = $queryResults;
		
	} else {
		echo "User not found";
	}

?>

<!DOCTYPE html>
<html>
<head>
	<?php
	include "head.php";
	include "header.php";
	include "loginForm.php";
	echo $head;
	?>
</head>
<body>
	<div id="container">
		<?php
		echo $header;
		
		echo $loginForm;		
		
		$db->close();
		?>
	</div>
</body>
</html>