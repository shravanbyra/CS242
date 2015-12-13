<?php
    include_once '../FilesEntry.php';
    include_once '../assignment entry.php';

    /**
     * This class is designed to test the functionality of the Assignement
     * Entry.
     *
     * @author Joshua Harris
     */

    class AssignmentEntryTest extends PHPUnit_Framework_TestCase {

        public function testFileEntryAdd() {
            $assignEntry = new AssignmentEntry();
            $fileEntry = new FilesEntry();
            $fileEntry->addFile("blah");
            $assignEntry->addFileEntry($fileEntry, "1");

            $entries = $assignEntry->getFileEntries();
            assert(count($entries) == 1);
        }


    }
?>