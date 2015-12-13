# Home page

<?php
include "server.php";

# $path = ' > current offers';

# select all weapons to display
$query = 'SELECT o.owner_id, o.num_keys, w.type, w.skin, w.wear, w.avg_price, (w.avg_price - o.num_keys) as bang
FROM offers o, weapons w
WHERE o.weapon_id = w.weapon_id
AND w.avg_price - o.num_keys > 0
ORDER BY bang DESC LIMIT 20';

if ($queryResults = $db->query($query)) {
	
} else {
	echo "There currently are no weapons available";
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
		
		echo '<table id="query-results">';
		echo '<tr><th class="results-header">Type</th><th class="results-header">Skin</th><th class="results-header">Wear</th><th class="results-header">Cost</th><th class="results-header">Average Price</th><th class="results-header">Difference</th></tr>';
		while ($row = $queryResults->fetch_assoc()) {
			echo '<tr id="weapon-offer"><td>' . $row["type"] . '</td><td>' . $row["skin"] . '</td><td>' . $row["wear"] . '</td><td>' . $row["num_keys"] . '</td> <td>' . $row["avg_price"] . '</td><td>' . $row["bang"] . '</td></tr>';
		}
		echo '</table>';
		echo $message;
		?>
	</div>
</body>
</html>