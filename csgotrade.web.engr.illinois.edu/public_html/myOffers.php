<?php
	include "server.php";
	
	$path = ' > my offers';
	
	
	$queryResults = $db->query('SELECT * FROM weapons WHERE 1');
	
	#$userQuery = 'DELETE FROM weapons WHERE type="' . $wpnType . '"';
	#if ($db->query($userQuery)) {
	#	$result = '<p>Succesfully deleted the data.</p>';
	#} else {
	#	$result = '<p>Failed to insert the data.<p/>';
	#}
?>

<!DOCTYPE html>
<html>
<head>
	<?php
	include "head.php";
	include "header.php";
	include "partials/queryDiv.php";
	include "partials/userInputDelete.php";
	include "partials/options.php";
	echo $head;
	?>
</head>
<body>
	<div id="container">
		<?php
		echo $header;
		
		echo '<table id="query-results">';
		echo '<tr><th class="results-header">Type</th><th class="results-header">Skin</th><th class="results-header">Wear</th><th class="results-header">Cost</th></tr>';
		while ($row = $queryResults->fetch_assoc()) {
			echo '<tr class="weapon-offer"><td>' . $row["type"] . '</td><td>' . $row["skin"] . '</td><td>' . $row["wear"] . '</td><td>' . $row["avg_price"] . '</td></tr>';
		}
		echo '</table>';
		
		$db->close();
		?>
	</div>
</body>
</html>