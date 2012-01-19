<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Busy Beaver 2" tapes="1" xml-version="3">
    <tape type="graphic">
        <name>Input / Output</name>
        <input/>
    </tape>
    <state final="no" height="50" id="42a48f32-3694-4c8f-9e6a-0f7ffd9356d8" start="yes" width="50" x="220" y="240">
        <name>q_s</name>
    </state>
    <state final="no" height="50" id="c2712a0e-c3e8-4daf-a743-e474fbbfcde5" start="no" width="50" x="470" y="200">
        <name>q_2</name>
    </state>
    <state final="yes" height="50" id="8c575131-d922-46de-b6ec-4318d821528f" start="no" width="50" x="700" y="200">
        <name>q_f</name>
    </state>
    <edge from="42a48f32-3694-4c8f-9e6a-0f7ffd9356d8" labelx="0" labely="10" to="c2712a0e-c3e8-4daf-a743-e474fbbfcde5">
        <via x="300" y="190"/>
        <via x="400" y="190"/>
        <transition id="4bda6901-1a56-4f62-9f84-ea9be7d5e8b4">
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
    <edge from="c2712a0e-c3e8-4daf-a743-e474fbbfcde5" labelx="0" labely="-5" to="42a48f32-3694-4c8f-9e6a-0f7ffd9356d8">
        <transition id="852fd952-7a76-4c04-8905-9a481af0d989">
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
    <edge from="42a48f32-3694-4c8f-9e6a-0f7ffd9356d8" labelx="0" labely="3" to="c2712a0e-c3e8-4daf-a743-e474fbbfcde5">
        <via x="310" y="263"/>
        <via x="410" y="263"/>
        <transition id="d7f33385-4a6d-496a-b49c-02c557a83d58">
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
    <edge from="c2712a0e-c3e8-4daf-a743-e474fbbfcde5" labelx="0" labely="15" to="8c575131-d922-46de-b6ec-4318d821528f">
        <transition id="f97f461b-31cf-425c-9043-a3b333a49e16">
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
