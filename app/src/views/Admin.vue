<template>
    <!-- <hello-world :msg="message"></hello-world> -->
    <div id="apiData">Hello world</div>
</template>

<script>
// import HelloWorld from "../components/HelloWorld.vue";

export default {
    name: "LandingPage",
    // components: {
    //     "hello-world": HelloWorld,
    // },
    // data() {
    //     return {
    //         message: "Admin component loaded",
    //     };
    // },
    mounted() {
        fetch('https://19e9-27-109-3-234.ngrok-free.app/test/partner/route')
                .then(response => response.json())
                .then(data => {
                    // Update the content of the <p> tag with the received data
                    document.getElementById('apiData').innerText = JSON.stringify(data);
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
    },
}
</script>