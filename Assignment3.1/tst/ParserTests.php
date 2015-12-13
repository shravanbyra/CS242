<?php
    include '../Parse.php';

    /**
     * created in order to test the Parser and make sure the
     * functionality is working as specified.
     *
     * @author Joshua Harris
     */

    class ParserTests extends PHPUnit_Framework_TestCase {

        /**
         * tests to make sure the parsing of the Assignment Names is
         * correct.
         */
        function testParsingAssignmentNamesFromSVNList() {
            $parser = new Parser();
            $parser->parse();
            $expectedNames = array("Assignment0", "Assignment1.0", "Assignment1.1",
                "Assignment1.2", "Assignment1.3", "Assignment2.0", "Assignment2.1");

            $list = $parser->getParsedInfo();

            assert(is_array($list));
            for($i = 0; $i < 7; $i += 1) {
                $name = $list[$i]->getName();
                echo $name;
                assert( $name == $expectedNames[$i]);
            }
        }




    }

?>