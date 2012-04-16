<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Double" tapes="2" xml-version="3">
    <author/>
    <description/>
    <tape type="LEGO">
        <name>Default graphic tape</name>
        <input>
            <symbol>0</symbol>
            <symbol>0</symbol>
        </input>
    </tape>
    <tape type="LEGO">
        <name>Default graphic tape</name>
        <input/>
    </tape>
    <state final="no" height="50" id="c962f005-4fad-4011-91a6-077f12970b34" start="yes" width="50" x="50" y="100">
        <name>New...</name>
    </state>
    <state final="no" height="50" id="c150bc4f-c424-4a34-9e31-6ab5969f328d" start="no" width="50" x="250" y="100">
        <name>New...</name>
    </state>
    <state final="yes" height="50" id="bdc92c0e-a2f3-42cf-89ba-a36177b81a6b" start="no" width="50" x="150" y="200">
        <name>New...</name>
    </state>
    <edge from="c962f005-4fad-4011-91a6-077f12970b34" labelx="0" labely="0" to="c150bc4f-c424-4a34-9e31-6ab5969f328d">
        <via x="180" y="150"/>
        <transition id="a1196c64-8e8e-46b0-b834-3c3826530403">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="c150bc4f-c424-4a34-9e31-6ab5969f328d" labelx="0" labely="0" to="c962f005-4fad-4011-91a6-077f12970b34">
        <via x="180" y="100"/>
        <transition id="1305d0c3-05ae-4db5-9f19-80ef2f758bfa">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="c962f005-4fad-4011-91a6-077f12970b34" labelx="0" labely="0" to="bdc92c0e-a2f3-42cf-89ba-a36177b81a6b">
        <transition id="3e8bbf1b-0d32-4cd0-ba0d-d06ccbc429c5">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
</machine>
