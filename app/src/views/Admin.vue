<template>
    <hello-world :msg="message"></hello-world>
</template>

<script>
import HelloWorld from "../components/HelloWorld.vue";

export default {
    name: "LandingPage",
    components: {
        "hello-world": HelloWorld,
    },
    data() {
        return {
            message: "Admin component loaded",
        };
    },
    mounted() {
        console.log("Admin component loaded");
    },
}
</script>