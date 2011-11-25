<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="test" tapes="3" xml-version="3">
	<tape type="graphic">
		<name>input tape</name>
		<master mac-address="00:16:53:13:53:BB">IPS_03</master>
		<slave mac-address="00:16:53:0F:DB:8E">NXT_03</slave>
		<input>
			<symbol>0</symbol>
			<symbol>1</symbol>
			<symbol>2</symbol>
			<symbol>2</symbol>
			<symbol>2</symbol>
		</input>
	</tape>
	<tape type="graphic">
		<name>second tape</name>
		<input>
			<symbol>#</symbol>
			<symbol>1</symbol>
			<symbol>#</symbol>
			<symbol>1</symbol>
			<symbol>#</symbol>
			<symbol>#</symbol>
		</input>
	</tape>
	<tape type="graphic">
		<name>third tape</name>
		<input>
			<symbol>#</symbol>
			<symbol>1</symbol>
			<symbol>#</symbol>
			<symbol>1</symbol>
			<symbol>#</symbol>
			<symbol>#</symbol>
		</input>
	</tape>
	<state id="0" start="yes" final="no">
		<name>q0</name>
	</state>
	<state id="1" start="no" final="yes">
		<name>q2</name>
	</state>
	<edge from="0" to="1">
		<transition id="0">
			<read>
				<symbol>0</symbol>
				<symbol>1</symbol>
				<symbol>2</symbol>
			</read>
			<write>
				<symbol>0</symbol>
				<symbol>1</symbol>
				<symbol>2</symbol>
			</write>
			<action>
				<direction>L</direction>
				<direction>N</direction>
				<direction>R</direction>
			</action>
		</transition>
		<transition id="1">
			<read>
				<symbol>0</symbol>
				<symbol>1</symbol>
				<symbol>2</symbol>
			</read>
			<write>
				<symbol>0</symbol>
				<symbol>1</symbol>
				<symbol>2</symbol>
			</write>
			<action>
				<direction>L</direction>
				<direction>N</direction>
				<direction>R</direction>
			</action>
		</transition>
	</edge>
</machine>
