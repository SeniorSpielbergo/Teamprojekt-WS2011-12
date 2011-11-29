<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="test" tapes="1" xml-version="3">
	<tape type="console">
		<name>Primary tape</name>
		<input>
			<symbol>#</symbol>
			<symbol>0</symbol>
	        <symbol>0</symbol>
	        <symbol>#</symbol>
	        <symbol>0</symbol>
	        <symbol>0</symbol>
	        <symbol>#</symbol>
		</input>
	</tape>
	<state id="0" start="yes" final="no" width="20" height="10" x="1" y="1">
        <name>qS</name>
    </state>
    <state id="1" start="no" final="no" width="20" height="10" x="1" y="3">
        <name>q0</name>
    </state>
    <state id="2" start="no" final="no" width="20" height="10" x="1" y="5">
        <name>q1</name>
    </state>
    <state id="3" start="no" final="no" width="20" height="10" x="3" y="5">
        <name>q2</name>
    </state>
    <state id="4" start="no" final="no" width="20" height="10" x="3" y="3">
        <name>q3</name>
    </state>
    <state id="5" start="no" final="no" width="20" height="10" x="3" y="1">
        <name>q4</name>
    </state>
    <state id="6" start="no" final="no" width="20" height="10" x="5" y="1">
        <name>q5</name>
    </state>
    <state id="7" start="no" final="yes" width="20" height="10" x="5" y="3">
        <name>qF</name>
    </state>
    <edge from="0" to="1">
    	<transition id="0">
    		<read>
    			<symbol>#</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>R</direction>
    		</action>    			
    	</transition>
    </edge>
    
    <edge from="1" to="1">
    	<transition id="1">
    		<read>
    			<symbol>0</symbol>
    		</read>
    		<write>
    			<symbol>0</symbol>
    		</write>
    		<action>
    			<direction>R</direction>
    		</action>    			
    	</transition>
    </edge>
    <edge from="1" to="2">
    	<transition id="2">
    		<read>
    			<symbol>#</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>R</direction>
    		</action> 
    	</transition>
    </edge>
   <edge from="2" to="2">
    	<transition id="3">
    		<read>
    			<symbol>0</symbol>
    		</read>
    		<write>
    			<symbol>0</symbol>
    		</write>
    		<action>
    			<direction>R</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="2" to="4">
    	<transition id="4">
    		<read>
    			<symbol>#</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>L</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="3" to="5">
    	<transition id="5">
    		<read>
    			<symbol>0</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>L</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="3" to="5">
    	<transition id="6">
    		<read>
    			<symbol>#</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>L</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="4" to="4">
    	<transition id="7">
    		<read>
    			<symbol>0</symbol>
    		</read>
    		<write>
    			<symbol>0</symbol>
    		</write>
    		<action>
    			<direction>L</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="4" to="5">
    	<transition id="8">
    		<read>
    			<symbol>#</symbol>
    		</read>
    		<write>
    			<symbol>0</symbol>
    		</write>
    		<action>
    			<direction>L</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="5" to="5">
    	<transition id="9">
    		<read>
    			<symbol>0</symbol>
    		</read>
    		<write>
    			<symbol>0</symbol>
    		</write>
    		<action>
    			<direction>L</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="5" to="6">
    	<transition id="10">
    		<read>
    			<symbol>#</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>R</direction>
    		</action> 
    	</transition>
    </edge>
    <edge from="6" to="7">
    	<transition id="11">
    		<read>
    			<symbol>0</symbol>
    		</read>
    		<write>
    			<symbol>#</symbol>
    		</write>
    		<action>
    			<direction>N</direction>
    		</action> 
    	</transition>
    </edge>
</machine>
