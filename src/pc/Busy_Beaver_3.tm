<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Busy Beaver 3" tapes="1" xml-version="3">
    <tape type="graphic">
        <name>Input / Output</name>
        <input/>
    </tape>
    <state final="no" height="50" id="d43a8ae5-4e8b-48e1-af92-ab8e5acfab28" start="yes" width="50" x="170" y="190">
        <name>q_S</name>
    </state>
    <state final="no" height="50" id="f557899a-a3a8-48b2-a086-36f48a8b70dc" start="no" width="50" x="480" y="160">
        <name>q_1</name>
    </state>
    <state final="no" height="50" id="f9809e0d-b8e2-449f-9dbd-70b431b14761" start="no" width="50" x="300" y="310">
        <name>q_2</name>
    </state>
    <state final="yes" height="50" id="5b841a2a-07b7-4cca-8014-aabca1d22689" start="no" width="50" x="450" y="430">
        <name>q_f</name>
    </state>
    <edge from="d43a8ae5-4e8b-48e1-af92-ab8e5acfab28" labelx="0" labely="5" to="f557899a-a3a8-48b2-a086-36f48a8b70dc">
        <transition id="79cac112-61e6-416e-94c4-61b06f59d92f">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="f557899a-a3a8-48b2-a086-36f48a8b70dc" labelx="0" labely="-10" to="d43a8ae5-4e8b-48e1-af92-ab8e5acfab28">
        <via x="420" y="120"/>
        <via x="230" y="120"/>
        <transition id="bf0bcbee-2752-4cb0-9ca9-664bae19d864">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="f557899a-a3a8-48b2-a086-36f48a8b70dc" labelx="0" labely="50" to="f557899a-a3a8-48b2-a086-36f48a8b70dc">
        <transition id="8ac57424-9825-4164-ae67-ef536b9a7cc2">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="d43a8ae5-4e8b-48e1-af92-ab8e5acfab28" labelx="1" labely="35" to="f9809e0d-b8e2-449f-9dbd-70b431b14761">
        <transition id="23c1b6f1-b393-4c7c-a237-d778640e60e5">
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
    <edge from="f9809e0d-b8e2-449f-9dbd-70b431b14761" labelx="1" labely="-35" to="f557899a-a3a8-48b2-a086-36f48a8b70dc">
        <transition id="77bb1ca6-995a-4300-bb49-08a9608fade4">
            <read>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="f9809e0d-b8e2-449f-9dbd-70b431b14761" labelx="1" labely="32" to="5b841a2a-07b7-4cca-8014-aabca1d22689">
        <transition id="02575cf6-a472-4597-a5df-ad788a2e9121">
            <read>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
</machine>
