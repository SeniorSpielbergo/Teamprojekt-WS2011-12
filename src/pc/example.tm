<?xml version="1.0" encoding="utf-8" standalone="no"?>
<machine name="test" tape="4">
    <input>
        <tape>
            <symbol>a</symbol>
            <symbol>b</symbol>
            <symbol>c</symbol>
            <symbol>d</symbol>
        </tape>
    </input>
    <input>
        <tape>
            <symbol>a</symbol>
            <symbol>b</symbol>
            <symbol>c</symbol>
            <symbol>d</symbol>
        </tape>
    </input>
    <input>
        <tape>
            <symbol>a</symbol>
            <symbol>b</symbol>
            <symbol>c</symbol>
            <symbol>d</symbol>
        </tape>
    </input>
    <input>
        <tape>
            <symbol>a</symbol>
            <symbol>b</symbol>
            <symbol>c</symbol>
            <symbol>d</symbol>
        </tape>
    </input>
    <state id="0" type="start">
        <name>q0</name>
    </state>
    <state id="1" type="normal">
        <name>q1</name>
    </state>
    <edge from="0" to="1">
        <transition type="0"/>
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
            <symbol>0</symbol>
            <symbol>1</symbol>
            <symbol>2</symbol>
        </action>
    </edge>
</machine>