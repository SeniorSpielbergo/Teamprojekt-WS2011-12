<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="XOR" tapes="3" xml-version="3">
    <author>David Wille</author>
    <description>This Turing machine calculates the XOR value of two binary digits.</description>
    <tape type="graphic">
        <name>Input 1</name>
        <input>
            <symbol>1</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Input 2</name>
        <input>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Output</name>
        <input/>
    </tape>
    <state final="no" height="50" id="0318c840-f162-41e5-b067-7aeaf6f018f2" start="yes" width="50" x="200" y="150">
        <name>q_s</name>
    </state>
    <state final="no" height="50" id="43a6ec95-9254-454e-8ea2-803ee7ccfcb0" start="no" width="50" x="450" y="150">
        <name>q_1</name>
    </state>
    <state final="no" height="50" id="6904a7dc-0985-4e1c-a64f-a0e4e97d0d4d" start="no" width="50" x="450" y="350">
        <name>q_2</name>
    </state>
    <state final="yes" height="50" id="e6e473c9-dbfa-4ee8-9d94-57936ca5ab30" start="no" width="50" x="200" y="350">
        <name>q_f</name>
    </state>
    <edge from="0318c840-f162-41e5-b067-7aeaf6f018f2" labelx="0" labely="30" to="0318c840-f162-41e5-b067-7aeaf6f018f2">
        <via x="230" y="130"/>
        <transition id="f669486a-21d6-428e-803e-197c1b440d1b">
            <read>
                <symbol>1</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="183d6cbe-a44b-4a88-a8bc-3caeca7af2c1">
            <read>
                <symbol>0</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="9b2663ab-c723-49ab-91af-f4e2e93dcf26">
            <read>
                <symbol>1</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="d872f0c6-23f1-4114-bd06-f16bd384c5e6">
            <read>
                <symbol>0</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="72ee3b96-4017-4bb3-85a1-d447d79ad866">
            <read>
                <symbol>1</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="183c83b0-dead-4e19-ac55-163764a904a4">
            <read>
                <symbol>0</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="452cd1b6-ac5b-49fb-9ba6-bd0bce1c2ae8">
            <read>
                <symbol>#</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="af536286-8a9c-485f-b644-74fba80dea11">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="0318c840-f162-41e5-b067-7aeaf6f018f2" labelx="0" labely="15" to="43a6ec95-9254-454e-8ea2-803ee7ccfcb0">
        <transition id="00da0c56-207a-47bb-a726-7a969e5fd21f">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="43a6ec95-9254-454e-8ea2-803ee7ccfcb0" labelx="0" labely="30" to="43a6ec95-9254-454e-8ea2-803ee7ccfcb0">
        <via x="470" y="130"/>
        <transition id="cc5a9853-247c-4d80-9109-3a57a66e956a">
            <read>
                <symbol>0</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="696507fb-6307-4df9-b001-eb337e15a579">
            <read>
                <symbol>1</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="34b84c90-d2cb-4d60-ba3e-e4f1b8aa2eb5">
            <read>
                <symbol>0</symbol>
                <symbol>1</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="10c0d1ab-372b-4b67-8b80-7f53b28bbaac">
            <read>
                <symbol>1</symbol>
                <symbol>1</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="43a6ec95-9254-454e-8ea2-803ee7ccfcb0" labelx="0" labely="85" to="6904a7dc-0985-4e1c-a64f-a0e4e97d0d4d">
        <transition id="c09bedf8-0aa3-4649-88b4-60347f35e0f7">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="7454c6ea-3081-46d5-a1f9-722e5dbeab9a">
            <read>
                <symbol>#</symbol>
                <symbol>1</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="86708f40-e968-423f-b0d8-6d715e8c6a28">
            <read>
                <symbol>0</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="d7544327-219b-4dca-a149-3042e92def7b">
            <read>
                <symbol>1</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="6904a7dc-0985-4e1c-a64f-a0e4e97d0d4d" labelx="0" labely="-15" to="e6e473c9-dbfa-4ee8-9d94-57936ca5ab30">
        <transition id="7a9c7808-0184-46f9-97c0-a51c9362400b">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="6904a7dc-0985-4e1c-a64f-a0e4e97d0d4d" labelx="0" labely="-40" to="6904a7dc-0985-4e1c-a64f-a0e4e97d0d4d">
        <via x="470" y="420"/>
        <transition id="d3101478-9b7e-4047-92fb-7cdcf70b2a4a">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="0fa2ca43-ca13-406c-a4f2-b3361920424c">
            <read>
                <symbol>#</symbol>
                <symbol>1</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="521cc9a2-a9a4-46b8-a147-3e64ee21b7f4">
            <read>
                <symbol>0</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="5320098b-8110-480f-9cb7-da9e0bb0ec59">
            <read>
                <symbol>1</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
</machine>
