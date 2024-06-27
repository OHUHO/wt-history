<template>
    <Layout>
        <div class="rn-section-gap timeline-wrapper pt--70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <SectionTitle
                            title="历史记录线"
                            data-aos="fade-up"
                            data-aos-delay="60"
                        />
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <Timeline :timeline-data="timelineData"/>
                    </div>
                </div>
            </div>
        </div>

        
    </Layout>
</template>

<script>
    import Layout from '../components/common/Layout';
    import SectionTitle from "../components/elements/sectionTitle/SectionTitle";
    import Timeline from "../components/elements/timeline/Timeline";

    export default {
        name: 'TimelinePage',
        components: {Timeline, SectionTitle, Layout},
        data() {
            return {
                timelineData: [
                    {
                        id: '1',
                        date: '(约)公元前1700000年',
                        title: '文明曙光',
                        description: '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。',
                        image: require('../assets/images/timeline/timeline-1.jpeg'),
                        workingStep: [
                            {
                                stepTitle: '历史事件',
                                stepDescription: '【事件1】 / 【事件2】 / 【事件34】 / <事件10> /<事件34>',
                            },
                            {
                                stepTitle: '词条标签',
                                stepDescription: '【早期人类】/【社会】/【石器】/【劳动】',
                            },
                        ]
                    }
                    
                ],
            }
        },
	    mounted() {
			this.getTimeLine()
	    },
	    methods:{
			getTimeLine(){
				this.getRequest("/common/getDynasties",'all').then(resp => {
					if (resp){
						this.timelineData = resp.obj
					}
				})
			}
	    },
    }
</script>
