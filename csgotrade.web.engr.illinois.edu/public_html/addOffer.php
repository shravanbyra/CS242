<?php
	include "server.php";
	
	$path = ' > add offer';
	
?>

<!DOCTYPE html>
<html>
<head>
	<?php
	include "head.php";
	include "header.php";
	include "weaponQueryFilter.php";
	include "partials/queryDiv.php";
	include "partials/userInputAdd.php";
	include "partials/options.php";
	echo $head;
	?>
</head>
<body>
	<div id="container">
		<?php
		echo $header;
		
		echo $filterOptions;
		
		$db->close();
		?>
	</div>
</body>
</html>