<template>
    <ul class="main-menu">
        <li><router-link to="/">主页</router-link></li>
        <li><router-link to="/timeline">时间线条</router-link></li>
        <li><router-link to="/maps">时空地图</router-link></li>
    </ul>
</template>

<script>
    import Icon from "../../icon/Icon";
    export default {
        name: 'Nav',
        components: {Icon}
    }
</script>
