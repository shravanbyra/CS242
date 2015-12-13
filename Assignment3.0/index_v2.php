<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>
<html>
    <?php
        include 'Parse.php';

        /**
         * Index file for the CS 242 Porfolio Site
         *
         * @author Joshua Harris
         */
        $svnData = null;
        $svnParser = new Parser();

        // Check to see if the data has been parsed recently and if not then set it
        if(isset($_SESSION['$svnData'])) {
            $svnData = $_SESSION['$svnData'];
        }
        else {
            $svnParser->parse();
            $svnData = $svnParser->getParsedInfo();
            $_SESSION['$svnData'] = $svnData;
        }
    ?>

    <body>
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
                foreach($svnData as $assignmentEntry) {
                    echo "<td>" . $assignmentEntry->getName() . "</td>";
                    $entries = $assignmentEntry->getFileEntries();
                    echo count($entries);
                    $entry = $entries[0];
                    echo "<td>" . $entry->getDate() . "</td>";
                    echo "<td>" . $entry->getRevision() . "</td>";
                }
            ?>
    </body>
</html>