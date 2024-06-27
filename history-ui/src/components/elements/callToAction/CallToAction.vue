<template>
    <div class="rn-callto-action clltoaction-style-default style-dy"
         :style="{'background-image': `url(${backgroundImage})`}">
        <div class="container">
            <div class="row row--0 align-items-center content-wrapper">
                <div class="col-lg-8 col-md-8">
                    <div class="inner">
                        <div class="content text-start">
                            <h1 class="display-one title h3" data-aos="fade-up" data-aos-delay="100">
	                            <span class="theme-gradient">{{ name }}</span>
                            </h1>
                            <p class="subtitle"
                               style="max-width: 100%"
                               v-html="description"
                               data-aos="fade-up"
                               data-aos-delay="60"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="call-to-btn text-start mt_sm--20 text-md-end"
                         data-aos="fade-up"
                         data-aos-delay="60">
                        <a class="btn-default btn-icon"
                           :href="`/map/${id}`">
                            查看地图
                            <Icon name="arrow-right" class="size-20"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Icon from '../../icon/Icon'

    export default {
        name: 'CallToAction',
        components: {Icon},
        props: {
            name: {
                type: String
            },
	        description: {
                type: String
            },
	        id:{
				type: Number
	        }
        },
	    data(){
			return{
				backgroundImage: require('../../../assets/images/dynasty/10006.jpeg')
			}
	    },
        computed: {
        
        }
    }
</script>
