<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="TMadd" tapes="1" xml-version="3">
    <tape type="graphic">
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
    <state final="no" height="50" id="0" start="yes" width="50" x="50" y="50">
        <name>qS</name>
    </state>
    <state final="no" height="50" id="1" start="no" width="50" x="50" y="150">
        <name>q0</name>
    </state>
    <state final="no" height="50" id="2" start="no" width="50" x="50" y="250">
        <name>q1</name>
    </state>
    <state final="no" height="50" id="3" start="no" width="50" x="50" y="250">
        <name>q2</name>
    </state>
    <state final="no" height="50" id="4" start="no" width="50" x="150" y="150">
        <name>q3</name>
    </state>
    <state final="no" height="50" id="5" start="no" width="50" x="150" y="50">
        <name>q4</name>
    </state>
    <state final="no" height="50" id="6" start="no" width="50" x="250" y="50">
        <name>q5</name>
    </state>
    <state final="yes" height="50" id="7" start="no" width="50" x="250" y="150">
        <name>qF</name>
    </state>
    <edge from="0" labelx="0" labely="0" to="1">
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
    <edge from="1" labelx="0" labely="0" to="1">
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
    <edge from="1" labelx="0" labely="0" to="2">
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
    <edge from="2" labelx="0" labely="0" to="2">
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
    <edge from="2" labelx="0" labely="0" to="4">
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
    <edge from="3" labelx="0" labely="0" to="5">
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
    <edge from="3" labelx="0" labely="0" to="5">
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
    <edge from="4" labelx="0" labely="0" to="4">
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
    <edge from="4" labelx="0" labely="0" to="5">
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
    <edge from="5" labelx="0" labely="0" to="5">
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
    <edge from="5" labelx="0" labely="0" to="6">
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
    <edge from="6" labelx="0" labely="0" to="7">
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
