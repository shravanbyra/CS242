<?php	
	include "server.php";
	include "partials/queryDiv.php";
	
	$path = ' > search';
	
	$wpnType = '';
	$wpnSkin = '';
	$wpnWear = '';
	
	if($_SERVER["REQUEST_METHOD"] == "POST") {
		$wpnType = $_POST['wpn-type'];
		$wpnSkin = $_POST['wpn-skin'];
		$wpnWear = $_POST['wpn-wear'];
	}
	
	$userQuery = 'SELECT * FROM weapons WHERE ';
		
	if ($wpnType != '') {
		$userQuery = $userQuery . 'type="' . $wpnType . '" AND ';
	}
	
	if ($wpnSkin != '') {
		$userQuery = $userQuery . 'skin="' . $wpnSkin . '" AND ';
	}
	
	if ($wpnWear != '') {
		$userQuery = $userQuery . 'wear="' . $wpnWear . '"';
			
	if ($userQuery != '') {
		$results = $db->query($userQuery);
	}
?>

<!DOCTYPE html>
<html>
<head>
	<?php
	include "head.php";
	include "header.php";
	echo $head;
	?>
</head>
<body>
	<div id="container">
		<?php
		echo $header;
		echo $queryDiv;
		echo '<p id="user-query">You searched "' . $userQuery . '"</p>';
		
		if ($results->num_rows > 0) {
			echo '<table id="query-results">';
			echo '<tr><th class="results-header">Type</th><th class="results-header">Skin</th><th class="results-header">Wear</th><th class="results-header">Cost</th></tr>';
			
			while ($row = $results->fetch_assoc()) {
				echo '<tr><td>' . $row["type"] . '</td><td>' . $row["skin"] . '</td><td>' . $row["wear"] . '</td><td>' . $row["avg_price"] . '</td></tr>';
			}
			echo '</table>';
			
		} else {
			echo "<p>The weapon you searched for was not found!</p>";
		}		
		
		$db->close();
		?>
	</div>
</body>
</html>