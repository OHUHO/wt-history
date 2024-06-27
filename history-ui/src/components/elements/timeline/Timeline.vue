<template>
    <div class="rn-timeline-wrapper timeline-style-one position-relative">
        <div class="timeline-line"/>

        <div class="single-timeline mt--50"
             v-for="(dynasty, index) in timelineData"
             :key="index">
            <div class="timeline-dot">
                <div class="time-line-circle"/>
            </div>
            <div class="single-content">
                <div class="inner">
                    <div class="row row--30 align-items-center">
                        <div class="order-2 order-lg-1 col-lg-6 mt_md--40 mt_sm--40">
                            <div class="content">
                                <span class="date-of-timeline"
                                      data-aos="fade-up"
                                      data-aos-delay="50">
                                    {{ dynasty.dynastyPeriod }}
                                </span>
                                <h2 class="title"
                                    data-aos="fade-up"
                                    data-aos-delay="80">
                                    {{ dynasty.dynastyName }}
                                </h2>
                                <p class="description"
                                   data-aos="fade-up"
                                   data-aos-delay="110">
                                    {{ dynasty.dynastyDescribe }}
                                </p>

                                <!--<div class="row row&#45;&#45;30">
                                    <div class="col-lg-6 col-md-6 col-12"
                                         v-for="(step, index) in dynasty.workingStep"
                                         :key="index"
                                         data-aos="fade-up"
                                         data-aos-delay="140">
                                        <div class="working-list">
                                            <h5 class="working-title"
                                                data-aos="fade-up" data-aos-delay="60">
                                                {{ step.stepTitle }}
                                            </h5>
                                            <p data-aos="fade-up"
                                               data-aos-delay="60">
                                                {{ step.stepDescription }}
                                            </p>
                                        </div>
                                    </div>
                                </div>-->
	                            
                                <div class="read-morebtn">
                                    <a class="btn-default btn-large round"
                                       @click.prevent="dynastyDetails(dynasty.dynastyId)"
                                       href="#"
                                       data-aos="fade-up"
                                       data-aos-delay="170">
                                        <span>查看更多</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="order-1 order-lg-2 col-lg-6">
                            <div class="thumbnail" data-aos="fade-up">
                                <img class="w-100"
                                     :src="dynasty.dynastyCover"
                                     alt="Corporate Vue Template"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</template>

<script>
    export default {
        name: 'Timeline',
        props: {
            timelineData: {},
        },
        computed: {
        },
	    methods:{
		    dynastyDetails(dynastyId){
				this.$router.push({path:`/dynasty/${dynastyId}`})
		    }
	    }
    }
</script>
