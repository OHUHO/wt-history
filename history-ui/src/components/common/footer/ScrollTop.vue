<template>
    <div class="rn-back-top"
         @click="scrollToTop"
         :class="[visible ? 'd-inline' : 'd-none']">
        <Icon name="chevron-up" size="27"/>
    </div>
</template>

<script>
    import Icon from '../../icon/Icon'

    export default {
        name: 'ScrollTop',
        components: {Icon},
        data() {
            return {
                visible: false
            }
        },
        methods: {
            scrollToTop() {
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth'
                });
            },
            toggleVisible() {
                const scrolled = document.documentElement.scrollTop;
                if (scrolled > 300) {
                    this.visible = true;
                } else if (scrolled <= 300) {
                    this.visible = false;
                }
            }
        },
        created() {
            window.addEventListener('scroll', this.toggleVisible);
        },
        mounted() {
            this.toggleVisible();
        },
        beforeDestroy() {
            window.removeEventListener('scroll', this.toggleVisible);
        }
    }
</script>