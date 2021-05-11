<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.patientName" label="Patient Name" />
          <v-text-field v-model="consultation.doctorName" label="Doctor Name" />
          <v-text-field v-model="consultation.startHour" label="Start Hour" />
          <v-text-field v-model="consultation.endHour" label="End Hour" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
            .create({
              patientName: this.consultation.patientName,
              doctorName: this.consultation.doctorName,
              startHour: this.consultation.startHour,
              endHour: this.consultation.endHour,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.consultations
            .edit({
              patientName: this.consultation.patientName,
              doctorName: this.consultation.doctorName,
              startHour: this.consultation.startHour,
              endHour: this.consultation.endHour,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
