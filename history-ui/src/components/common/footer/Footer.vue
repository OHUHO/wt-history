<template>
    <div>
        <Copyright/>
	    
	    <!-- 回到顶部 -->
        <ScrollTop/>
    </div>
</template>

<script>
    import Copyright from './Copyright'
    import ScrollTop from './ScrollTop'

    export default {
        name: 'Footer',
        components: {ScrollTop, Copyright},
    }
</script>
