<template>
    <div class="section-title" >
        <h2 class="title w-600">{{title}}</h2>
        <p class="description b1">{{description}}</p>
    </div>
	
</template>

<script>
    export default {
        name: 'SectionTitle',
        props: {
            title: {
                type: String,
                default: null
            },
            description: {
                type: String,
                default: null
            },
        },
        
    }
</script>
