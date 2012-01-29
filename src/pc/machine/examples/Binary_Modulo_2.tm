<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="Binary Modulo 2" tapes="2" xml-version="3">
    <author>David Wille</author>
    <description>This Turing machine calculates the modulus 2 of the given binary digit.</description>
    <tape type="graphic">
        <name>Input</name>
        <input>
            <symbol>1</symbol>
            <symbol>0</symbol>
            <symbol>0</symbol>
            <symbol>1</symbol>
        </input>
    </tape>
    <tape type="graphic">
        <name>Output</name>
        <input/>
    </tape>
    <state final="no" height="50" id="0228de31-27f4-4944-a9c4-2ff39df5d177" start="yes" width="50" x="100" y="100">
        <name>q_s</name>
    </state>
    <state final="no" height="50" id="9d3d36b5-935a-4b41-b584-f5ddd90c9bb3" start="no" width="50" x="300" y="100">
        <name>q_1</name>
    </state>
    <state final="yes" height="50" id="194bcbc8-9398-4edf-b0b4-5339597117f0" start="no" width="50" x="300" y="300">
        <name>q_f</name>
    </state>
    <edge from="0228de31-27f4-4944-a9c4-2ff39df5d177" labelx="0" labely="20" to="0228de31-27f4-4944-a9c4-2ff39df5d177">
        <via x="110" y="80"/>
        <transition id="56d86d54-450f-4dbd-8b09-6db547030ad1">
            <read>
                <symbol>0</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
        <transition id="6fcd223b-8b44-4745-8d47-df91062bea97">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>R</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="0228de31-27f4-4944-a9c4-2ff39df5d177" labelx="0" labely="15" to="9d3d36b5-935a-4b41-b584-f5ddd90c9bb3">
        <transition id="fa841843-f721-4a14-98c1-f4b0ba34563e">
            <read>
                <symbol>#</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>*</symbol>
            </write>
            <action>
                <direction>L</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
    <edge from="9d3d36b5-935a-4b41-b584-f5ddd90c9bb3" labelx="0" labely="65" to="194bcbc8-9398-4edf-b0b4-5339597117f0">
        <transition id="cb754388-8c69-4db4-8aaa-871c81d72aec">
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
                <direction>N</direction>
            </action>
        </transition>
        <transition id="e009812a-6121-4f76-8600-0f55b2bdc2f5">
            <read>
                <symbol>1</symbol>
                <symbol>*</symbol>
            </read>
            <write>
                <symbol>*</symbol>
                <symbol>1</symbol>
            </write>
            <action>
                <direction>N</direction>
                <direction>N</direction>
            </action>
        </transition>
    </edge>
</machine>
