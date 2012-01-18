<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Binary Addition" tapes="3" xml-version="3">
    <tape type="graphic">
        <name>Input</name>
        <input>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>#</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>0</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Output</name>
        <input/>
    </tape>
    <tape type="graphic">
        <name>Action</name>
        <input/>
    </tape>
    <state final="no" height="50" id="3a7230f4-ab4e-4fd3-99af-6d76c04d53de" start="yes" width="50" x="200" y="100">
        <name>qS</name>
    </state>
    <state final="no" height="50" id="063f75d1-5b74-4c12-82e4-6366b1dc0e21" start="no" width="50" x="500" y="100">
        <name>q1</name>
    </state>
    <state final="no" height="50" id="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a" start="no" width="50" x="500" y="300">
        <name>q2</name>
    </state>
    <state final="no" height="50" id="fa46f6c2-191c-468d-8463-0f1d593f7c32" start="no" width="50" x="800" y="300">
        <name>q3</name>
    </state>
    <state final="no" height="50" id="a840ab53-8eb6-4e89-a65f-2b6ceb24c577" start="no" width="50" x="800" y="400">
        <name>q4</name>
    </state>
    <state final="no" height="50" id="c993fba3-0f60-4b03-b7d4-bc9d0f1d9f6c" start="no" width="50" x="800" y="500">
        <name>q5</name>
    </state>
    <state final="no" height="50" id="99b1fad3-a0a8-45d4-b7e3-d72237b8e55e" start="no" width="50" x="500" y="500">
        <name>q6</name>
    </state>
    <state final="no" height="50" id="07e35116-9702-4a06-81be-725b202c1831" start="no" width="50" x="200" y="500">
        <name>q7</name>
    </state>
    <state final="no" height="50" id="04cb89a6-70a2-48e7-8bdf-354d8a85f494" start="no" width="50" x="200" y="400">
        <name>q8</name>
    </state>
    <state final="yes" height="50" id="a40241b3-c773-48cf-acd4-bce53b4b81b7" start="no" width="50" x="200" y="300">
        <name>qF</name>
    </state>
    <edge from="3a7230f4-ab4e-4fd3-99af-6d76c04d53de" labelx="0" labely="-60" to="3a7230f4-ab4e-4fd3-99af-6d76c04d53de">
        <via x="180" y="120"/>
        <transition id="a9e72380-2ab8-42fe-bc89-fed6327a398f">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="d373a20c-7335-422f-823c-5d529a899af9">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="3a7230f4-ab4e-4fd3-99af-6d76c04d53de" labelx="0" labely="5" to="063f75d1-5b74-4c12-82e4-6366b1dc0e21">
        <transition id="3c645b0e-2ead-4f53-845c-af5ebb546d1a">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="063f75d1-5b74-4c12-82e4-6366b1dc0e21" labelx="0" labely="60" to="063f75d1-5b74-4c12-82e4-6366b1dc0e21">
        <via x="570" y="120"/>
        <transition id="c11d3e68-a365-47ee-a5b6-5112eb6fecc8">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="e6c53984-37fd-4a8e-988e-cb1a449ff1fb">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="063f75d1-5b74-4c12-82e4-6366b1dc0e21" labelx="0" labely="55" to="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a">
        <transition id="de2b75cb-e5db-48d3-9fd3-54dcdd4138fc">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
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
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a" labelx="0" labely="5" to="fa46f6c2-191c-468d-8463-0f1d593f7c32">
        <transition id="00e740f7-247e-4e63-97b6-0e4c7a659319">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a" labelx="0" labely="-60" to="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a">
        <via x="480" y="320"/>
        <transition id="09480a1c-9d86-4b57-b3d1-c2457b19c606">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="fa46f6c2-191c-468d-8463-0f1d593f7c32" labelx="0" labely="60" to="fa46f6c2-191c-468d-8463-0f1d593f7c32">
        <transition id="98e30944-ec6a-4fce-9f27-4ccd5d69e193">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="1a48cfd8-632f-44c3-bdac-a0e5f4d2bc34">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="fa46f6c2-191c-468d-8463-0f1d593f7c32" labelx="0" labely="65" to="a840ab53-8eb6-4e89-a65f-2b6ceb24c577">
        <transition id="435e5518-0217-446b-b758-a27296ecd50e">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="a840ab53-8eb6-4e89-a65f-2b6ceb24c577" labelx="0" labely="60" to="a840ab53-8eb6-4e89-a65f-2b6ceb24c577">
        <transition id="59032566-801b-4c8a-81be-4a0fff82edf7">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="a840ab53-8eb6-4e89-a65f-2b6ceb24c577" labelx="0" labely="65" to="c993fba3-0f60-4b03-b7d4-bc9d0f1d9f6c">
        <transition id="640f5f5c-5927-4eb1-96f1-92b5450a0d53">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="1b816a53-dffa-4c33-aacf-356f6d2111d2">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="c993fba3-0f60-4b03-b7d4-bc9d0f1d9f6c" labelx="0" labely="60" to="c993fba3-0f60-4b03-b7d4-bc9d0f1d9f6c">
        <transition id="33707ca3-ceac-41dd-b140-0e911ce69fb8">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="d048b92e-d902-4a74-9ba5-59ba6c76e085">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="c993fba3-0f60-4b03-b7d4-bc9d0f1d9f6c" labelx="0" labely="-5" to="99b1fad3-a0a8-45d4-b7e3-d72237b8e55e">
        <transition id="5cb297b8-8b9f-42f0-994d-b5e08eb65471">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="99b1fad3-a0a8-45d4-b7e3-d72237b8e55e" labelx="0" labely="-20" to="99b1fad3-a0a8-45d4-b7e3-d72237b8e55e">
        <via x="520" y="570"/>
        <transition id="476ec24d-a67f-4574-8104-ce1633cddd81">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="fd27c2e8-5885-40cf-8602-941a145e6932">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="99b1fad3-a0a8-45d4-b7e3-d72237b8e55e" labelx="0" labely="-55" to="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a">
        <transition id="b5bf7ca2-16ea-4e76-952e-a6cde9395d4d">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="ad43fe0f-3b5f-45d4-94b9-ec7eb4b1783a" labelx="0" labely="37" to="07e35116-9702-4a06-81be-725b202c1831">
        <transition id="a6e20279-6e72-4b7b-94d7-ed51f4848961">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="07e35116-9702-4a06-81be-725b202c1831" labelx="0" labely="-20" to="07e35116-9702-4a06-81be-725b202c1831">
        <via x="220" y="570"/>
        <transition id="b091749f-71b6-4ff8-a9f7-f0c0962dcb1b">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
        <transition id="fd20658c-6709-45e0-b671-9c1e9c1ea652">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="07e35116-9702-4a06-81be-725b202c1831" labelx="0" labely="55" to="04cb89a6-70a2-48e7-8bdf-354d8a85f494">
        <transition id="0c075501-2da1-43ca-bd91-3aac98cc5ad5">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="04cb89a6-70a2-48e7-8bdf-354d8a85f494" labelx="0" labely="55" to="a40241b3-c773-48cf-acd4-bce53b4b81b7">
        <transition id="cd0d6a67-8a2f-46fb-87e0-909947ea7f54">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>#</symbol>
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
    <edge from="04cb89a6-70a2-48e7-8bdf-354d8a85f494" labelx="0" labely="-60" to="04cb89a6-70a2-48e7-8bdf-354d8a85f494">
        <via x="180" y="420"/>
        <transition id="fe0a8635-1f9d-4b65-823c-03a0a75f3afe">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>0</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
        <transition id="b0066f96-c033-4261-b3d6-e16faff132b2">
            <read>
                <symbol>*</symbol>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>R</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <textbox height="40" width="70" x="700" y="100">
        <text>copy
inputword</text>
    </textbox>
    <textbox height="50" width="110" x="60" y="290">
        <text>write outputword
(left number)</text>
    </textbox>
    <textbox height="50" width="80" x="360" y="260">
        <text>decrement
right number</text>
    </textbox>
    <textbox height="50" width="80" x="1000" y="400">
        <text>increment
left number</text>
    </textbox>
    <textbox height="50" width="80" x="1000" y="260">
        <text>goto
left number</text>
    </textbox>
    <textbox height="60" width="60" x="690" y="400">
        <text>goto
right
number</text>
    </textbox>
    <textbox height="60" width="60" x="360" y="470">
        <text>if right 
number 
is all 0's</text>
    </textbox>
    <textbox height="60" width="270" x="510" y="10">
        <text>CAUTION: if using a LEGO tape please remember to 
insert enough leading zero's for the left number</text>
    </textbox>
    <textbox height="60" width="370" x="50" y="10">
        <text>This machine takes two binary numbers and adds them together.
The two binary numbers have to be given on tape Input devided by one #.
The result will be provided on tape Output.</text>
    </textbox>
    <frame height="70" width="730" x="50" y="90"/>
    <frame height="330" width="240" x="50" y="280"/>
    <frame height="140" width="410" x="350" y="250"/>
    <frame height="220" width="330" x="760" y="390"/>
    <frame height="220" width="300" x="460" y="390"/>
    <frame height="140" width="330" x="760" y="250"/>
</machine>
