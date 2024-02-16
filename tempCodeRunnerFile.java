private void start(JButton btn) {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (parkingLot.get(btn).isOccupied()) {
                    Thread.sleep(1000);
                    parkingLot.get(btn).timeParked++;
                    publish(parkingLot.get(btn).timeParked);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                Integer value = chunks.get(chunks.size() - 1);
                System.out.println(parkingLot.get(btn).getLicensePlate() + "'s parked time is " + value + " seconds");
            }

            @Override
            protected void done() {
                // 작업이 끝났을 때 호출
                try {
                    System.out.println("Parking fee is " + parkingLot.get(btn).calculateParkingFee() + " won");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }