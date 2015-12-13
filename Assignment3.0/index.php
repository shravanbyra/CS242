<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<html>
	<body>
		<?php 
			$names = array();
			$dates = array();
			$versions = array();
			$count = 0;
		
			$svnList = simplexml_load_file("svn_list.xml");
			
			# Go through each entry and look for the last entry of an assignment
			foreach (($svnList->list[0]) as $assignment) {
					# Use currentEntry's name because it should have just the assignment name
					$names[] = (string)$assignment->name;
					$dates[] = (string)$assignment->commit->date;
					$versions[] = (string)$assignment->commit['revision'];
			}
			
			$finalNames = array();
			$finalDates = array();
			$finalRevisions = array();
			$target = $names[0];
			for($i = 0; $i < count($names); $i++){
				if(strpos($names[$i], $target) === false){
					$finalNames[] = $target;
					$finalDates[] = $dates[$i - 1];
					$finalRevisions[] = $versions[$i - 1];
				
					$target = $names[$i];
				}
			}
			
			$end = count($names) - 1;
			$finalNames[]  = $target;
			$finalDates[] = $dates[$end];
			$tempVal = $versions[$end];
			$finalRevisions[] = $tempVal;
			
		?>
		
		<h1>Joshua Harris CS 242 Project Profile</h1>
		</br>
		<h2>Assignment Listing</h2>
		<p>Welcome to my CS 242 Profile Please Select a project to get started!</p>
		</br>
		<table>
			<tr>
				<td><b>Name</b></td>
				<td><b>Date</b></td>
				<td><b>Version</b></td>
			</tr>
			<?php 
				for($i = 0; $i < count($names); $i++) {
					$link = "entry.php?name=" . $finalNames[$i] . "&revision=" . $finalRevisions[$i];
					echo "<tr>";
					echo "<td><a href=" . $link . ">" . $finalNames[$i] . "   </a></td>";
					echo "<td>" . $finalDates[$i] . "   </td>";
					echo "<td>" . $finalRevisions[$i] . "   </td>";
					echo "</tr>";
				}
			?>
	
		</table>
		
	</body>
</html>