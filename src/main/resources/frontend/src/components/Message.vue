<template>
  <div class="col mt-5">
    <div class="row">
      <div class="col">
        <div v-if="message.messageOwner? message.messageOwner: false" class="msg-font mb-2">
          {{ message.messageOwner.username }}: {{ message.message_id }}
        </div>

        <h5 :class="{ isWarning: message.warningPost }">
          {{ message.messageContent }}
        </h5>

        <div class="msg-font">{{ getDate }}</div>
      </div>
      <div class="col d-flex justify-content-end align-middle">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="currentColor"
          class="bi bi-trash trash align-text-center"
          viewBox="0 0 16 16"
          @click="deleteMessage()"
        >
          <path
            d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
          />
          <path
            fill-rule="evenodd"
            d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
          />
        </svg>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    message: Object,
  },
  computed: {
    getDate() {
      return new Date(this.message.timestamp).toLocaleString("sv-SE");
    },
  },
  methods: {
    async deleteMessage() {
      const response = await fetch(
        `/api/v1/messages/${this.message.message_id}`,
        {
          method: "DELETE",
        }
      );
      console.log(response);
      if (response.status === 204) {
        await this.$store.dispatch("fetchThread", {
          forumId: this.$route.params.forum_id,
          threadId: this.$route.params.thread_id,
        });
      }
    },
  },
};
</script>
<style scoped>
.msg-font {
  font-size: 0.8rem;
  font-style: oblique;
}

.isWarning {
  color: red;
}
</style>
