<template>
    <ul class="map-style">
        <li v-for="(map, index) in mapList"
            :key="index"
            @click.prevent="mapDetails(map.dynastyId)">
            <a :href="`/map/${map.dynastyId}`">
	            <span class=" map-name b1">{{map.dynastyName}}</span>
            </a>
        </li>
    </ul>
</template>

<script>
    export default {
        name: 'Map',
        props: {
            mapList: {},
        },
	    methods:{
			mapDetails(dynastyId){
				this.$router.push({path:`/map/${dynastyId}`})
			}
	    }
    }
</script>
