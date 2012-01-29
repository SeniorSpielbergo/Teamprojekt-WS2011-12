<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="|1|^2 = |0|" tapes="3" xml-version="3">
    <author>David Wille</author>
    <description>This Turing machine checks whether the squared number of 1s is the same as the number 0s.</description>
    <tape type="graphic">
        <name>Input</name>
        <input>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>1</symbol>
            <symbol>1</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>0</name>
        <input/>
    </tape>
    <tape type="graphic">
        <name>1</name>
        <input/>
    </tape>
    <state final="no" height="50" id="8aec3d24-5fd5-49ec-a546-8508a8ddfdb2" start="yes" width="50" x="550" y="50">
        <name>q_s</name>
    </state>
    <state final="no" height="50" id="cb74d026-e12f-4687-b372-e2e90e2d204a" start="no" width="50" x="550" y="200">
        <name>q_1</name>
    </state>
    <state final="no" height="50" id="d8c3c1e2-49cd-46e6-b4a9-41288270c226" start="no" width="50" x="150" y="200">
        <name>q_2</name>
    </state>
    <state final="yes" height="50" id="4e734091-7822-4054-8b75-2ee767708b60" start="no" width="50" x="550" y="400">
        <name>q_f</name>
    </state>
    <state final="no" height="50" id="ac20dd38-d2f0-4063-a123-19c607ba9b01" start="no" width="50" x="150" y="600">
        <name>q_3</name>
    </state>
    <state final="no" height="50" id="2035fed3-7bc2-4f12-8c56-3e938923929b" start="no" width="50" x="550" y="600">
        <name>q_4</name>
    </state>
    <state final="no" height="50" id="fd6e8fb4-a0ff-44bd-8e60-9aabd3f78f62" start="no" width="50" x="950" y="200">
        <name>q_6</name>
    </state>
    <state final="no" height="50" id="df01cffe-7ed4-4f80-afbc-94daf1b0d99e" start="no" width="50" x="950" y="600">
        <name>q_5</name>
    </state>
    <edge from="8aec3d24-5fd5-49ec-a546-8508a8ddfdb2" labelx="0" labely="85" to="cb74d026-e12f-4687-b372-e2e90e2d204a">
        <transition id="67ce7843-18ba-41bb-a799-0d07340fdff8">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="cb74d026-e12f-4687-b372-e2e90e2d204a" labelx="0" labely="-5" to="d8c3c1e2-49cd-46e6-b4a9-41288270c226">
        <transition id="51ce659c-ebca-48a2-b9f5-dbd8afe55d64">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="cb74d026-e12f-4687-b372-e2e90e2d204a" labelx="0" labely="85" to="4e734091-7822-4054-8b75-2ee767708b60">
        <transition id="c5256d5e-6985-457f-aa10-7f04ba72c536">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="d8c3c1e2-49cd-46e6-b4a9-41288270c226" labelx="0" labely="0" to="ac20dd38-d2f0-4063-a123-19c607ba9b01">
        <via x="100" y="310"/>
        <via x="100" y="530"/>
        <transition id="e8681ce3-6b37-463c-a5d6-4fd04dc7529a">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="ac20dd38-d2f0-4063-a123-19c607ba9b01" labelx="0" labely="-90" to="d8c3c1e2-49cd-46e6-b4a9-41288270c226">
        <via x="250" y="530"/>
        <via x="250" y="310"/>
        <transition id="c9ce0cc7-c361-4f33-9165-d6e526766fdd">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="ac20dd38-d2f0-4063-a123-19c607ba9b01" labelx="0" labely="5" to="2035fed3-7bc2-4f12-8c56-3e938923929b">
        <transition id="025c5bd3-6744-4660-a6f1-b374272fb3f8">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="2035fed3-7bc2-4f12-8c56-3e938923929b" labelx="0" labely="85" to="4e734091-7822-4054-8b75-2ee767708b60">
        <transition id="72788eb1-0068-4f0c-a8db-559c2aae956e">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="d8c3c1e2-49cd-46e6-b4a9-41288270c226" labelx="0" labely="42" to="4e734091-7822-4054-8b75-2ee767708b60">
        <transition id="9ef7ce8f-3f4b-407a-8db6-aadf6970fc16">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="fd6e8fb4-a0ff-44bd-8e60-9aabd3f78f62" labelx="0" labely="-5" to="cb74d026-e12f-4687-b372-e2e90e2d204a">
        <transition id="87459bf1-977e-4a62-a26f-5f2a6b479dcf">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>L</direction>
            </action>
        </transition>
    </edge>
    <edge from="df01cffe-7ed4-4f80-afbc-94daf1b0d99e" labelx="0" labely="47" to="4e734091-7822-4054-8b75-2ee767708b60">
        <transition id="0404c92c-228b-441c-87be-bccb99aa87ca">
            <read>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="2035fed3-7bc2-4f12-8c56-3e938923929b" labelx="0" labely="5" to="df01cffe-7ed4-4f80-afbc-94daf1b0d99e">
        <transition id="99121813-0f62-4601-983f-1f8391f9ed7d">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="fd6e8fb4-a0ff-44bd-8e60-9aabd3f78f62" labelx="0" labely="-80" to="df01cffe-7ed4-4f80-afbc-94daf1b0d99e">
        <via x="890" y="320"/>
        <via x="890" y="530"/>
        <transition id="a3767868-768d-484a-aa46-bfdc6b537643">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="df01cffe-7ed4-4f80-afbc-94daf1b0d99e" labelx="0" labely="0" to="fd6e8fb4-a0ff-44bd-8e60-9aabd3f78f62">
        <via x="1050" y="530"/>
        <via x="1050" y="320"/>
        <transition id="85dcce5e-8424-470c-8cd4-411a51b4768f">
            <read>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>1</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>L</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
    <edge from="8aec3d24-5fd5-49ec-a546-8508a8ddfdb2" labelx="-1" labely="-30" to="8aec3d24-5fd5-49ec-a546-8508a8ddfdb2">
        <via x="530" y="80"/>
        <transition id="b1f9e56e-0834-4cba-a103-97faca2ce2fe">
            <read>
                <symbol>0</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>0</symbol>
                <symbol>#</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="452be026-274b-412b-b3be-a26d54bc14a8">
            <read>
                <symbol>1</symbol>
                <symbol>#</symbol>
                <symbol>#</symbol>
            </read>
            <write>
                <symbol>#</symbol>
                <symbol>#</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
                <direction>R</direction>
            </action>
        </transition>
    </edge>
</machine>
