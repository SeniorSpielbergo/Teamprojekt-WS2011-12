<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Busy Beaver 1" tapes="1" xml-version="3">
    <tape type="graphic">
        <name>Input / Output</name>
        <input/>
    </tape>
    <state final="no" height="50" id="58d59bc2-db7e-4489-bab6-e8f42b813d9a" start="yes" width="50" x="150" y="200">
        <name>q_s</name>
    </state>
    <state final="yes" height="50" id="daa3ef05-1ca1-4b99-a2d3-5c78fc12b6f4" start="no" width="50" x="300" y="200">
        <name>q_f</name>
    </state>
    <edge from="58d59bc2-db7e-4489-bab6-e8f42b813d9a" labelx="0" labely="10" to="58d59bc2-db7e-4489-bab6-e8f42b813d9a">
        <via x="190" y="130"/>
        <transition id="99520a78-cd84-4590-9532-f621a72d458f">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="58d59bc2-db7e-4489-bab6-e8f42b813d9a" labelx="0" labely="5" to="daa3ef05-1ca1-4b99-a2d3-5c78fc12b6f4">
        <transition id="42ae3a2b-26ba-433a-90e6-0601c59fb159">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
</machine>
