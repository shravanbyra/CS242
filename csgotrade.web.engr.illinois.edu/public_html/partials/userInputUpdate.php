<?php
	$queryDiv = '<div id="search-options">
	<form action="../update.php?go" method="post">
		<ul>
			<li>Type:<input type="text" name="wpn-type-old"></li>
			<li>Skin:<input type="text" name="wpn-skin-old"></li>
			<li>Wear:<input type="text" name="wpn-wear-old"></li>
			<li>Cost:<input type="text" name="wpn-cost-old"></li>
		</ul>
		<ul>
			<li>Type:<input type="text" name="wpn-type-new"></li>
			<li>Skin:<input type="text" name="wpn-skin-new"></li>
			<li>Wear:<input type="text" name="wpn-wear-new"></li>
			<li>Cost:<input type="text" name="wpn-cost-new"></li>
			<li><input type="submit" name="wpn-submit" value="Update"></li>
		</ul>
	</form>
	</div>
	<br><br>';
?>