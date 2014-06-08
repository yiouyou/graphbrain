(ns graphbrain.disambig.entityguesser
  (:require [graphbrain.db.gbdb :as gbdb]
            [graphbrain.db.id :as id]
            [graphbrain.db.maps :as maps]))

(defn guess
  [gbdb name eid ctxts]
  (let [base-id (id/sanitize name)
        can-mean (gbdb/pattern->edges gbdb ["r/+can_mean" base-id "*"] ctxts)]
    (if (empty? can-mean)
      (maps/eid->entity eid)
      (let [syns (map #(gbdb/getv gbdb (id/eid->id (nth (maps/ids %) 2)) ctxts)
                      can-mean)]
        (apply max-key :degree syns)))))

(defn guess-eid
  [gbdb name eid ctxts]
  (let [entity (guess gbdb name eid ctxts)]
    (id/local->global (:eid entity))))
