<template>
    <i v-html="iconSvg(name)" :class="`size-${size} ${iconClass}`"/>
</template>

<script>
    import feather from 'feather-icons'

    export default {
        name: 'Icon',
        props: {
            name: {
                type: String,
                required: true
            },
            size: {
                type: String|Number,
                default: 16
            },
            iconClass: {
                type: String,
                default: ''
            }
        },
        methods: {
            iconSvg(icon) {
                return feather.icons[icon].toSvg()
            }
        }
    }
</script>